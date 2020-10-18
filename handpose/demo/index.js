/**
 * @license
 * Copyright 2020 Google LLC. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =============================================================================
 */

import * as handpose from '@tensorflow-models/handpose';
import * as tf from '@tensorflow/tfjs-core';
// import '@tensorflow/tfjs-backend-cpu';
// import * as tfjsWasm from '@tensorflow/tfjs-backend-wasm';
import '@tensorflow/tfjs-backend-webgl';
//
// tfjsWasm.setWasmPath(
//     `https://cdn.jsdelivr.net/npm/@tensorflow/tfjs-backend-wasm@${
//         tfjsWasm.version_wasm}/dist/tfjs-backend-wasm.wasm`);

// const motionFrame = [];
let swipeTop = false;
let swipeLeft = false;
let currGesture = -1;

// const shell = require('shelljs');


let videoWidth, videoHeight, rafID, ctx, canvas, ANCHOR_POINTS,
    scatterGLHasInitialized = false, scatterGL, fingerLookupIndices = {
      thumb: [0, 1, 2, 3, 4],
      indexFinger: [0, 5, 6, 7, 8],
      middleFinger: [0, 9, 10, 11, 12],
      ringFinger: [0, 13, 14, 15, 16],
      pinky: [0, 17, 18, 19, 20],
    }; // for rendering each finger as a polyline

const VIDEO_WIDTH = 1280;
const VIDEO_HEIGHT = 1000;

const renderPointcloud = false;

const state = {
  backend: 'webgl',
};
//
// const stats = new Stats();
// stats.showPanel(0);
// document.body.appendChild(stats.dom);

if (renderPointcloud) {
  state.renderPointcloud = false;
}


function drawPoint(y, x, r) {
  ctx.beginPath();
  ctx.arc(x, y, r, 0, 2 * Math.PI);
  ctx.fill();
}

function drawKeypoints(keypoints) {
  const keypointsArray = keypoints;

  for (let i = 0; i < keypointsArray.length; i++) {
    const y = keypointsArray[i][0];
    const x = keypointsArray[i][1];
    drawPoint(x - 2, y - 2, 3);
  }

  const fingers = Object.keys(fingerLookupIndices);
  for (let i = 0; i < fingers.length; i++) {
    const finger = fingers[i];
    const points = fingerLookupIndices[finger].map((idx) => keypoints[idx]);
    drawPath(points, false);
  }
}

function drawPath(points, closePath) {
  const region = new Path2D();
  region.moveTo(points[0][0], points[0][1]);
  for (let i = 1; i < points.length; i++) {
    const point = points[i];
    region.lineTo(point[0], point[1]);
  }

  if (closePath) {
    region.closePath();
  }
  ctx.stroke(region);
}

let model;

async function setupCamera() {
  if (!navigator.mediaDevices || !navigator.mediaDevices.getUserMedia) {
    throw new Error(
        'Browser API navigator.mediaDevices.getUserMedia not available');
  }

  const video = document.getElementById('video');
  const stream = await navigator.mediaDevices.getUserMedia({
    'audio': false,
    'video': {
      facingMode: 'user',
      frameRate: {ideal: 15, max: 15},
      width: VIDEO_WIDTH,
      height: VIDEO_HEIGHT,
    },
  });
  video.srcObject = stream;

  return new Promise((resolve) => {
    video.onloadedmetadata = () => {
      resolve(video);
    };
  });
}

async function loadVideo() {
  const video = await setupCamera();
  video.play();
  return video;
}

async function main() {
  await tf.setBackend(state.backend);
  model = await handpose.load({detectionConfidence: 0.5});
  let video;

  try {
    video = await loadVideo();
  } catch (e) {
    let info = document.getElementById('info');
    info.textContent = e.message;
    info.style.display = 'block';
    throw e;
  }

  videoWidth = video.videoWidth;
  videoHeight = video.videoHeight;

  canvas = document.getElementById('output');
  canvas.width = videoWidth;
  canvas.height = videoHeight;
  video.width = videoWidth;
  video.height = videoHeight;

  ctx = canvas.getContext('2d');
  ctx.clearRect(0, 0, videoWidth, videoHeight);
  ctx.strokeStyle = 'white';
  ctx.fillStyle = 'blue';

  ctx.translate(canvas.width, 0);
  ctx.scale(-1, 1);

  // These anchor points allow the hand pointcloud to resize according to its
  // position in the input.
  ANCHOR_POINTS = [
    [0, 0, 0], [0, -VIDEO_HEIGHT, 0], [-VIDEO_WIDTH, 0, 0],
    [-VIDEO_WIDTH, -VIDEO_HEIGHT, 0],
  ];


  landmarksRealTime(video);
}

const landmarksRealTime = async (video) => {
  async function frameLandmarks() {
    // stats.begin();
    ctx.drawImage(
        video, 0, 0, videoWidth, videoHeight, 0, 0, canvas.width,
        canvas.height);
    const predictions = await model.estimateHands(video);


    if (predictions.length > 0) {
      const result = predictions[0].landmarks;
      drawKeypoints(result, predictions[0].annotations);

      console.log(predictions);

      const palmTop = predictions[0].boundingBox['topLeft'][1];
      // const palmSide = predictions[0].boundingBox['bottomRight'][0];

      // console.log('PALM TOP'+palmTop);

      if (palmTop<-100 && !swipeLeft) {
        swipeTop = true;
        currGesture = 200;
      }

      if (swipeTop && !swipeLeft) {
        if (palmTop>10) {
          console.log('STEP INTO');
          // const output = await exec('ls', {encoding: 'utf-8'}); // the default is 'buffer'
          // console.log('Output was:\n', output);

          currGesture=0;
        }

        currGesture-=1;
        if (currGesture<=0) {
          swipeTop=false;
        }
      }

      // if (palmSide < 600 && !swipeTop) {
      //   swipeTop = true;
      //   currGesture = 200;
      // }

      // if (swipeLeft && !swipeTop) {
      //   if (palmSide>1200) {
      //     console.log('STEP OVER');
      //     currGesture=0;
      //   }
      //
      //   currGesture-=1;
      //   if (currGesture<=0) {
      //     swipeTop=false;
      //   }
      // }
    }
    // stats.end();
    rafID = requestAnimationFrame(frameLandmarks);
  };

  frameLandmarks();
};

navigator.getUserMedia = navigator.getUserMedia ||
    navigator.webkitGetUserMedia || navigator.mozGetUserMedia;

main();
