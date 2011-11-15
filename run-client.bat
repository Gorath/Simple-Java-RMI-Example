@echo off
echo 	========   Java batch run for RMI Client   ========
echo 	========      (c) Gareth Jones 2011        ========
cd bin
echo on
java -Djava.security.policy=../openall.policy client/RMIClient %1 %2
@echo off
cd ../
echo on