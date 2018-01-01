aapt p -v -f -m -M AndroidManifest.xml -I pkg/android.jar  -S res -J . 

javac pkg/app/*.java -nowarn -classpath cls;pkg/android.jar;pkg/map/slf4j.jar;pkg/map/osmdroid.jar;pkg/server/backendless.jar  -d cls   -Xlint:deprecation 

dx --dex --output=dex/classes.dex  cls pkg/server/backendless.jar pkg/map/osmdroid.jar pkg/map/slf4j.jar

