macos下面安装docker

下载并且安装最新版本的docker(1.12.0)
https://download.docker.com/mac/stable/Docker.dmg


下载并且安装最新版本的docker-toolbox(1.12.0)
https://www.docker.com/products/docker-toolbox

进行测试
docker --version
docker ps
docker run hello-world

如果有问题， 检查环境变量、版本等参数

测试nginx
docker run -d -p 80:80 --name webserver nginx
启动以后，就可以访问localhost

查看所有docker进程
docker ps -a

启动/停止正在运行的docker container
docker start/stop ps_id

移除一个docker container
docker rm ps_id

以交互的方式进入到cache容器里面
docker exec -it cache bash
