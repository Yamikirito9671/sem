name: CI - seMethods Lab 2
on: [push]

jobs:
  build:
    runs-on: ubuntu-22.04
    steps:
      - name: Checkout repository
        uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'

      # ✅ Run Maven one level up
      - name: Build with Maven
        run: mvn -B clean package
        working-directory: ..

      # ✅ Build Docker image one level up too
      - name: Build Docker image
        run: docker build -t semethods-image .
        working-directory: ..

      - name: Run Docker container
        run: docker run --name semethods-container -d semethods-image

      - name: Show container logs
        run: docker logs semethods-container || true
