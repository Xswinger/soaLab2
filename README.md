# Instruction for configure SSL:

### 1. Genkey private key for first service (in CN we define ip/resource name, that we need to connect): 
keytool -genkeypair -alias first-service -keyalg RSA -keysize 2048 -keystore first-service.jks -validity 3650 -storepass password -keypass password -dname "CN=10.5.0.1, OU=se, O=itmo, L=ru, S=SPB, C=RU"

### 2. Genkey private key for second service: 
keytool -genkeypair -alias second-service -keyalg RSA -keysize 2048 -keystore second-service.jks -validity 3650 -storepass password -keypass password -dname "CN=second-service, OU=se, O=itmo, L=ru, S=SPB, C=RU"

### 3. Export first service private key from JKS to P12:
keytool -importkeystore -srckeystore first-service.jks -srcalias first-service -destkeystore first-service.p12 -deststoretype PKCS12 -deststorepass password

### 4. Export second service private key from JKS to P12:
keytool -importkeystore -srckeystore second-service.jks -srcalias second-service -destkeystore second-service.p12 -deststoretype PKCS12 -deststorepass password

### 5. Export first service private key from P12 to KEY and cert to new cert:
openssl pkcs12 -in first-service.p12 -nocerts -nodes -out first-service.key
openssl pkcs12 -in first-service.p12 -clcerts -nokeys -out first-service.crt

### 6. Export second service private key from P12 to KEY and cert to new cert:
openssl pkcs12 -in second-service.p12 -nocerts -nodes -out second-service.key
openssl pkcs12 -in second-service.p12 -clcerts -nokeys -out second-service.crt

### 7. Import first service cert in truststore (in first service Dockerfile):
keytool -importcert -keystore $JAVA_HOME/lib/security/cacerts -storepass password -alias first-service -trustcacerts -file first-service.crt -noprompt

### 8. Import first service cert in truststore (in second service Dockerfile):
keytool -importcert -keystore $JAVA_HOME/lib/security/cacerts -storepass password -alias second-service -trustcacerts -file second-service.crt -noprompt

### 9. Join first service private key and cert in PEM file for harproxy:
cat first-service.key first-service.crt > first-service.pem

### 10. Join first service private key and cert in PEM file for harproxy:
cat second-service.key second-service.crt > second-service.pem
