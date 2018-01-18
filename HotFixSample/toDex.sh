#!/usr/bin/env bash

# 多个class 空格分隔
cd app/build/intermediates/classes/debug/
jar cvf DexUtil.jar com/apkfuns/hotfixsample/DexUtil.class

# dx 文件在 {android sdk}/build-tools/23.0.1/dx
/Users/pengwei/Android/android-sdk/build-tools/23.0.1/dx --dex --output=DexUtil_dex.jar  DexUtil.jar
mv DexUtil_dex.jar ../../../../../