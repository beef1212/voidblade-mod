# 공허의 검 (Voidblade) — Fabric 모드 소스

**대상:** Minecraft Java Edition 1.21.1 / Fabric Loader / Fabric API / Java 21

## PC에 Gradle을 설치하지 않고 JAR 만들기 (GitHub Actions)

이 프로젝트에는 GitHub가 온라인 빌드 컴퓨터에서 JAR를 만들도록 설정한 워크플로가 들어 있습니다. 내 컴퓨터에는 Gradle을 설치할 필요가 없습니다.

1. GitHub에 로그인하고 새 저장소(repository)를 만듭니다. 예: `voidblade-mod`.
2. 이 ZIP 파일을 압축 해제합니다.
3. 압축을 푼 **폴더 안의 파일과 폴더 전체**를 새 GitHub 저장소에 업로드합니다. `.github/workflows/build.yml` 파일도 꼭 포함해야 합니다. 저장소 루트에서 `build.gradle`, `settings.gradle`, `gradle.properties`, `src` 폴더가 바로 보여야 합니다.
4. 업로드가 끝나면 저장소의 **Actions** 탭을 엽니다.
5. 왼쪽에서 **Build Voidblade Mod**를 선택합니다.
6. **Run workflow** 버튼이 보이면 눌러 다시 실행할 수 있습니다. 처음에는 `main` 또는 `master` 브랜치에 파일을 올리면 자동으로 실행됩니다.
7. 작업이 초록색 체크 표시로 끝나면 해당 실행 기록을 엽니다.
8. 아래쪽 **Artifacts**에서 `voidblade-fabric-1.21.1`을 다운로드합니다.
9. 다운로드한 ZIP을 풀고 안의 `voidblade-1.0.0.jar`를 Minecraft의 `.minecraft/mods` 폴더에 복사합니다.

**주의:** JAR 파일은 GitHub Actions 실행이 성공한 뒤에만 생성됩니다. 이 패키지 자체는 컴파일된 JAR가 아닙니다. GitHub에서 오류가 나면 Actions 실행 기록의 빨간색 실패 단계와 로그를 확인해야 합니다.

## 포함된 기능
- 전투 아이템 탭에 등록되는 공허의 검
- 높은 기본 공격력과 내구도, 화염 면역
- 우클릭 특수 능력 `공허 파동`: 전방 주변 생명체에게 피해와 넉백, 영혼 불꽃/베기 파티클
- 특수 능력 재사용 대기시간 4초
- 한국어/영어 아이템 이름과 툴팁
- 원본 `model_source.bbmodel` 및 원본 텍스처 9개 보존

## 게임에서 사용하기
1. Minecraft Java 1.21.1을 실행할 수 있도록 Fabric Loader와 Fabric API를 설치합니다.
2. 생성된 `voidblade-1.0.0.jar`를 `.minecraft/mods`에 넣습니다.
3. 치트가 켜진 월드에서 `/give @s voidblade:voidblade` 명령으로 검을 얻습니다.
4. 좌클릭은 기본 검 공격, 우클릭은 공허 파동입니다. 특수 능력은 4초 재사용 대기시간이 있습니다.

## 모델 관련 안내
`model_source.bbmodel`은 사용자가 올린 원본을 보존한 파일입니다. 원본은 자유형 mesh 기반이라 Minecraft 기본 아이템 모델 JSON에서 `.bbmodel`을 직접 렌더링할 수 없습니다. 현재 게임 내 손/아이콘 모델은 기본 handheld 아이템 모델을 사용합니다. 원본 3D mesh를 게임에 정확히 렌더링하거나 별도의 커스텀 공격 애니메이션을 재생하는 기능은 아직 포함되어 있지 않습니다.
