# STEP per creare immagine docker e push su dockerhub
docker build -t ping-service .
docker login
docker tag ping-service mircocennamo82/my-repo:ping-service
docker push mircocennamo82/my-repo:ping-service