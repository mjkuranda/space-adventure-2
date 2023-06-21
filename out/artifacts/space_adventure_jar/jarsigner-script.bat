// -Djava.library.path=lib\natives\windows

set PATH=C:\Program Files\AdoptOpenJDK\jdk-20\bin

keytool -genkey -alias mjkuranda -keyalg RSA -keypass password -storepass password -keystore keystore.jks
jarsigner -keystore keys -storepass password space-adventure.jar mjkuranda
jarsigner -verify -keystore keystore.jks space-adventure.jar


sign4j.exe javaw -jar jsign-2.0.jar --alias 'YOURURL' --keystore 'keystore.pfx' --storepass 'yourPassword' --storetype PKCS12 'yourprogram.exe'