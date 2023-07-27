# Use the base GraalVM image for native image compilation
FROM quay.io/quarkus/ubi-quarkus-native-image:jdk-17

# Set the working directory in the container
WORKDIR /fx-deals

# Copy the compiled native executable to the container
COPY target/*-runner /app

# Expose the port on which the Quarkus application listens (change as needed)
EXPOSE 8080

# Set the entry point to run the Quarkus application
CMD ["./fx-deals-importer-runner"]
