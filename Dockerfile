FROM openjdk:19
COPY ./out/production/Party/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java","Party"]