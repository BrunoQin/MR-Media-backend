gradle build
cp build/libs/MR_Media-0.0.1-SNAPSHOT.war build/libs/ROOT.war
rm build/libs/MR_Media-0.0.1-SNAPSHOT.war
./scp.sh build/libs/ROOT.war root@112.74.50.130:/opt/apache-tomcat-8.0.39/webapps/
rm build/libs/ROOT.war
