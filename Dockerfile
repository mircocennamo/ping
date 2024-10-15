FROM ubuntu:latest
LABEL authors="mirco.cennamo"

RUN apt-get update && apt-get install -y openjdk-21-jdk
RUN apt-get update && apt-get install -y curl
RUN apt-get update && apt-get install -y iputils-ping
RUN apt-get update && apt-get install -y net-tools
RUN apt-get update && apt-get install -y vim
RUN apt-get update && apt-get install -y wget
RUN apt-get update && apt-get install -y telnet
RUN apt-get update && apt-get install -y dnsutils
RUN apt-get update && apt-get install -y iproute2
#RUN apt-get update && apt-get install -y iperf
#RUN apt-get update && apt-get install -y iperf3
#RUN apt-get update && apt-get install -y nmap
#RUN apt-get update && apt-get install -y tcpdump
#RUN apt-get update && apt-get install -y traceroute
#RUN apt-get update && apt-get install -y mtr
#RUN apt-get update && apt-get install -y iftop
#RUN apt-get update && apt-get install -y htop
#RUN apt-get update && apt-get install -y iotop
#RUN apt-get update && apt-get install -y sysstat
#RUN apt-get update && apt-get install -y strace
RUN apt-get update && apt-get install -y lsof
#RUN apt-get update && apt-get install -y ltrace
#RUN apt-get update && apt-get install -y jq

#RUN apt-get update && apt-get install -y git
#RUN apt-get update && apt-get install -y maven



# Imposta la variabile d'ambiente JAVA_HOME e aggiunge il binario di Java al PATH
ENV JAVA_HOME /usr/lib/jvm/openjdk-17-jdk
ENV PATH $JAVA_HOME/bin:$PATH

# Imposta la directory di lavoro nel container
WORKDIR /app

# Copia il file JAR generato dall'applicazione Spring Boot nella directory di lavoro
COPY target/ping-service-0.1.37-SNAPSHOT.jar /app/app.jar

# Esponi la porta su cui l'applicazione ascolterà
EXPOSE 8080

# Comando per eseguire l'applicazione Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]