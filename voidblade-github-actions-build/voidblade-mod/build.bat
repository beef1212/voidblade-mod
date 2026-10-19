@echo off
chcp 65001 >nul
echo Java 21과 Gradle이 설치되어 있어야 합니다.
gradle build
if errorlevel 1 pause
