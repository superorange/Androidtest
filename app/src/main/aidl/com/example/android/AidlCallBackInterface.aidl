// AidlCallBackInterface.aidl
package com.example.android;
import com.example.android.AidlObject;
// Declare any non-default types here with import statements

interface AidlCallBackInterface  {
  void callBack(in AidlObject object,String text);
}
