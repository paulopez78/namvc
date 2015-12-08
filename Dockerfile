FROM qlik/gradle

ADD . /namvc
RUN cd /namvc && gradle build
WORKDIR /namvc/build/libs
EXPOSE 8000
ENTRYPOINT java -jar namvc.jar
