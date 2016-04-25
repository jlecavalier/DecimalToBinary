#!/bin/bash
echo Assembling App
gradle --info assembleDebug
echo Installing App
adb install -r ./build/outputs/apk/DecimalToBinary-debug.apk
echo Good to go