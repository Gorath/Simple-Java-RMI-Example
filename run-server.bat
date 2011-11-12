@echo off
echo 	========   Java batch run for RMI Server   ========
echo 	========      (c) Gareth Jones 2011        ========
cd bin
echo on
java -cp . -Djava.security.policy=../openall.policy server/RMIServer
@echo off 
cd ../
echo on