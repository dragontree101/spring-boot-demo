docker build -t dragon/mysql .

if [ $? -eq 0 ];then
    echo "docker build success"
else
    echo "docker build fail"
    exit -1
fi

docker run -d -p 3306:3306 --name dragon-mysql dragon/mysql
