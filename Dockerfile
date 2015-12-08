FROM qlik/gradle

COPY . /app
RUN gradle build
WORKDIR /app/build/libs
EXPOSE 8000
ENTRYPOINT java -jar namvc.jar
