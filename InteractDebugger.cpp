#include <iostream>
#include <windows.h>
#include <stdlib.h>
#include <assert.h>
#include <winuser.h>
#include <regex>
#include <fstream>
#include <string.h> 
using namespace std;

int GetLineNumber(string line);
string ReadClipboardData();
string GetLineFromFile(string filename, int n);
struct KeyCommand
{
    string command;
    int keyCode;
};
static struct KeyCommand keys[] = {
    {"F5", 116},
    {"F7", 118},
    {"F8", 119},
    {"F9", 120},
    {"F10", 121}
};
static string outputFile = "code.txt";
void F(int keyCode) {
    keybd_event(keyCode, 0, 0, 0);
    keybd_event(keyCode, 0, KEYEVENTF_KEYUP, 0);
}

int FN(string command) {
    for (int i = 0; i < 5; i++) {
        if (command.find(keys[i].command, 0) != string::npos) {
            return keys[i].keyCode;
        }
    }
    return 0;
}
void SHIFT(int f) {
    keybd_event(16, 0, 0, 0);
    F(f);
    keybd_event(16, 0, KEYEVENTF_KEYUP, 0);

}
void Copy() {
    Sleep(1000);
    keybd_event(17, 0, 0, 0);
    keybd_event(67, 0, 0, 0);
    keybd_event(67, 0, KEYEVENTF_KEYUP, 0);
    keybd_event(17, 0, KEYEVENTF_KEYUP, 0);
}
void CTRL(int f, BOOL shift) {
    keybd_event(17, 0, 0, 0);
    if (shift) {
        SHIFT(f);
    }
    else {
        F(f);
    }
    keybd_event(17, 0, KEYEVENTF_KEYUP, 0);
}
string ReadClipboardData()
{
    HANDLE h;
    if (!OpenClipboard(NULL))
        exit(EXIT_FAILURE);
    h = GetClipboardData(CF_TEXT);
    char* text = (char*)h;
    string s = (string)text;
    CloseClipboard();
    return s;
}
int GetLineNumber(string line) {
    int colon = line.find(':');
    int comma = line.find(',');
    int num = atoi(line.substr(colon + 1, comma - colon).c_str());
    return num;
}
string GetLineFromFile(string filename, int n) {
    string line;
    ifstream f(filename);
    for (int i = 0; i < n; i++)
    {
        getline(f, line);
    }
    return line;
}

// This gets called by winapi for every window on the desktop
BOOL CALLBACK EnumWindowsProc(HWND windowHandle, LPARAM lParam) {
    DWORD searchedProcessId = (DWORD)lParam;  // This is the process ID we search for (passed from BringToForeground as lParam)
    DWORD windowProcessId = 0;
    GetWindowThreadProcessId(windowHandle, &windowProcessId); // Get process ID of the window we just found
    if (searchedProcessId == windowProcessId) {  // Is it the process we care about?
        SetForegroundWindow(windowHandle);  // Set the found window to foreground
        return FALSE;  // Stop enumerating windows
    }
    return TRUE;  // Continue enumerating
}
void BringToForeground(DWORD processId) {
    EnumWindows(&EnumWindowsProc, (LPARAM) processId);
}
void CallCommand(string command) {
  BOOL ctrl = command.find("CTRL", 0) != string::npos;
  BOOL shift = command.find("SHIFT", 0) != string::npos;
  int fn = FN(command);
  if (ctrl && shift) {
      CTRL(fn, TRUE);
  }
  else if (ctrl) {
      CTRL(fn, FALSE);
  }
  else if (shift) {
      SHIFT(fn);
  }
  else {
      F(fn);
  } 
  Copy();
}
void WriteToFile(string text) {
    ofstream f;
    f.open(outputFile);
    f << text;
    f.close();
}
int main(int argc, char* argv[])
{
    if (argc != 4) {
        cout << "Please pass in the correct number of arguments\n";
        return EXIT_FAILURE;
    }
    int pid = atoi(argv[1]);
    string command = argv[2];
    string filename = argv[3];
    BringToForeground(pid);
    CallCommand(command);
    string clip = ReadClipboardData();
    int linenum = GetLineNumber(clip);
    string line = GetLineFromFile(filename, linenum);
    WriteToFile(line);
    cout << "clip: " << clip << " linenum: " << linenum << endl;
    cout << "Line From File: " << GetLineFromFile(filename, linenum) << endl;

}   
