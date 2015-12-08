FROM qlik/gradle

COPY . /app
WORKDIR /app
EXPOSE 8000

