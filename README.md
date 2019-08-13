
처음 저장소를 만들면 기본적으로 master 브랜치가 생성됩니다. 위 그림은 master 브랜치로부터 새로운 브랜치를 생성하고 병합하는 과정을 간단하게 보여주고 있습니다. 브랜치를 사용하면 기존의 소스 코드나 다른 개발자의 작업에 영향을 주지 않고 물리적으로 완전히 분리된 작업을 할 수 있습니다.

<br />



<div align="center">
  <img src="./img/workflow_03_git_flow.png" alt="git-flow" />
  <p>
    git-flow
  </p>
</div>

위 그림은 Git을 사용한 전체 개발 흐름을 한눈에 보여주는 그림입니다. 개발자는 각각의 브랜치가 어떤 역할을 하는지 명확하게 이해해야 합니다.

> **(1) Upstream/master** : 현재 운영 중인 버전과 동일한 상태의 브랜치.
>
> **(2) Upstream/develop** : 다음 출시 버전에 추가될 기능을 개발하는 브랜치.
>
> **(3) Origin/develop** : Fork 한  개별 저장소에 존재하며, 다음 출시 버전을 위해서 개발자가 개별적으로 관리하는 브랜치입니다.
>
> **(4) hotfix** : 운영 중인 버전에서 발생한 버그를 수정하는 브랜치. master 브랜치로부터 갈라져 나온 브랜치입니다.
>
> - 네이밍 규칙 `hotfix-버전` (e.g. hotfix-1.0.1)
> - 버전은 버그를 수정하여 반영할 버전 넘버를 의미합니다.
>
> **(5) feature** : 추가할 기능을 개발하는 브랜치. develop 브랜치로부터 갈라져 나온 브랜치입니다.
>
> - 네이밍 규칙 `feature-기능명` (e.g. feature-profile)
> - 기능명은 동사가 아닌 명사를 사용합니다.
>
> **(6) fix** : QA 혹은 개발 중에 발생한 버그를 수정하는 브랜치.
>
> - 네이밍 규칙 `fix-버그명` (e.g. fix-profile-modificaion)
> - 버그명은 동사가 아닌 명사를 사용합니다.

master, develop 브랜치는 항상 유지되는 메인 브랜치, feature, hotfix, fix 브랜치는 일정 기간만 유지되는 보조 브랜치입니다.

<br />



#### (1) 기능 개발하기

<div align="center">
  <img src="./img/workflow_04_feature.png" alt="feature" />
</div>

> 새로운 기능을 개발할 때, 다음 가이드를 따릅니다.
>
> **1)** Local/develop 브랜치로 체크아웃(checkout)을 한 후에 Upstream/develop 브랜치와 동기화가 되어 있는지 확인합니다. 되어있지 않다면 풀 & 리베이스(pull & rebase)를 합니다. rebase 가 완료되면 Origin/develop으로 push 를 하여 Origin까지 작업 상태를 동기화합니다. 
>
> - `rebase(리베이스)`는 브랜치의 뿌리를 다시 설정하는 명령입니다. Upstream/develop 브랜치의 최신 상태를 pull(풀) 하여 받아오면, Local/develop에 Upstream/develop으로부터 분기된 새로운 히스토리가 생성됩니다. Local/develop에 새롭게 생성된 commit HEAD가 Upstream/develop 브랜치의 commit HEAD를 가리키도록(동기화)  뿌리를 재설정해 주어야 하는데, 이때 사용하는 것이 rebase 입니다.
>
> ```shell
> 명령어 : git pull --rebase [remote 저장소 이름] [branch 이름]
> ```
>
> **2)** Local/develop 브랜치로부터 feature-profile 브랜치를 생성합니다. 생성과 동시에 feature-profile 브랜치로 체크아웃을 합니다.
>
> - `checkout(체크아웃)`
>
> ```shell
> 명령어 : git checkout -b [branch 이름]
> ```
>
> **3)** feature-profile 브랜치에서 열심히 개발을 합니다. 개발을 마치고 생성/수정/삭제 commit 을 남깁니다. commit 은 `최대한 의미있게` 남겨야 합니다. 되도록이면 하나의 commit 만을 남기는 것을 권장합니다. [commit 규칙 참조](#2.-Git-commit-규칙)
>
> - e.g. 프로필 생성기능 추가, 프로필 수정기능 추가, 프로필 삭제 기능 추가 세 개의 commit => squash(스퀘시)를 이용하여 세 개의 commit 을 '프로필 생성/수정/삭제 기능 추가'라는 하나의 commit 으로 합칠 수 있습니다.
>
> **4)** Local/feature-profile 브랜치에서 개발이 마무리되면, Origin/feature-profile 브랜치로 commit 이력을 push 합니다.
>
> **5)** Origin/feature-profile 브랜치로부터 Upstream/develop에 `pull request(풀리퀘스트)`를 합니다.
>
> - `pull request`란 변경한 작업을 중앙 저장소의 특정 브랜치(일반적으론 Upstream/develop)에 merge 요청을 하는 것입니다. pull request를 통해 다른 개발자에게 코드 리뷰를 요청할 수 있습니다. 당장 merge 해야 하는 것이 아니기 때문에, 협업하는 개발자들과 의견을 공유하며 더 나은 수준의 코드를 짜기 위해 노력해야 합니다. 
