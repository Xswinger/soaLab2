1) keytool -importkeystore -srckeystore keystore.jks -srcalias first_service -destkeystore keystore.p12 -deststoretype PKCS12 -deststorepass password
2) openssl pkcs12 -in keystore.p12 -nodes -nocerts -out server.key
3) keytool -exportcert  -keystore keystore.jks -alias first-service -keypass password -storepass password -file first-service.crt
4) openssl x509 -inform der -in first-service.crt -out server.pem
5) cat server.pem server.key > haproxy.pem