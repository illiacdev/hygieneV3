FROM openjdk:19
EXPOSE 8080
#VOLUME /usr/src/myapp/H.A.S
COPY ./dist /usr/src/myapp
WORKDIR /usr/src/myapp
#RUN javac Main.java
CMD ["java", "-jar","app.jar"]
#ENTRYPOINT ["tail", "-f", "/dev/null"]
