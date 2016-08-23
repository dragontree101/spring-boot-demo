docker build -t dragon/redis .
docker run -d -p 6379:8888 --name dragon-redis dragon/redis
