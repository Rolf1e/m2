Cours de master deuxieme annee de Tigran SLAMA


- [POOA](https://github.com/Rolf1e/m2/tree/master/pooa)
- [JEE](https://github.com/Rolf1e/m2/tree/master/jee)
- [Web Mobile](https://github.com/Rolf1e/m2/tree/master/webmobile)
  - [TP1](https://github.com/Rolf1e/m2/tree/master/webmobile/TP1)
  - [TP2](https://github.com/Rolf1e/m2/tree/master/webmobile/TP2)
  - [TP3](https://github.com/Rolf1e/m2/tree/master/webmobile/TP3)
  - [TP4](https://github.com/Rolf1e/m2/tree/master/webmobile/TP4)


To [clone](https://askubuntu.com/questions/460885/how-to-clone-only-some-directories-from-a-git-repository) only one folder:

```bash

git clone --depth 1 [repo root] [name of destination directory]
cd [name of destination directory]
git filter-branch --prune-empty --subdirectory-filter [path to sub-dir] HEAD

```

