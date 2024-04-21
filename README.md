#### To package the application as a JAR run :
 ###### ``` mvnw.cmd clean install ```  (make sure JAVA_HOME is set)

#### Or to package as an image  (docker should be installed) run : 
 ###### ``` mvnw.cmd springboot:build-image ```

#### The job have two parameters : 
 ###### inputFilePath : file path of the input file 
 ###### resultDestination : the destination folder of the result file
