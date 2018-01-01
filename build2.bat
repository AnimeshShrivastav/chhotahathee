aapt p -v -f  -I pkg/android.jar  -M AndroidManifest.xml  -S res -F app.unsign.apk  dex 

jarsigner -keystore app.keystore -storepass kopt6025 -keypass kopt6025 -signedjar app.sign.apk app.unsign.apk mykey

zipalign -v -f 4 app.sign.apk app.apk

adb install app.apk

