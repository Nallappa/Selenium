
  WinWait("[CLASS:#32770]","",4)

; Set input focus to the edit control of Upload window using the handle returned by WinWait

  ControlFocus("File Upload","","Edit1")

  Sleep(2000)

; Set the File name text on the Edit field

  ControlSetText("File Upload", "", "Edit1", "D:\Latest_Selenium\Latest_Selenium\lord.jpg")

  Sleep(2000)

; Click on the Open button

  ControlClick("File Upload", "","Button1");