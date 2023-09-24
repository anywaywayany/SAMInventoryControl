# Getting started

**Clone repository**

*Clones it in the current folder and creates the folder SAMInventoryControl. If a custom folder name is needed append a foldername after the command. (git clone ..... .git <foldername>* 

*ssh*
```sh
git@github.com:anywaywayany/SAMInventoryControl.git
```

*http*
```sh
https://github.com/anywaywayany/SAMInventoryControl.git
```

# Git Cheatsheet
**Get from remote origin**

*doesn't change local repository, displays differences between local and remote repository*
```sh
git fetch
```
*changes local repository*
```sh
git pull
```

**Push to remote origin**

*push local repository to remote origin*
```sh
git push
```

**Check local files whether there are any changes**
```sh
git status
```
**Add files to staging area in order to commit them**

*add one or multiple files to the staging are*
```sh
git add <file> <file>
```

*adds complete folder and subfolders/files in subfolders*
```sh
git add .
```
**commits**

*commit with message/for short commit messages*
```sh
git commit -m "MESSAGE"
```
*opens up an editor/for long commit messages*
```sh
git commit 
```

## branches/features TODO
## merging (TODO)
## git configuration localy (TODO)
## Create ssh key and add to git locally and github account (TODO)

## Recommended worklow (still wop)

- regularly fetch from remote origin
- always fetch before pushing local repository to remote. In order to check whether there are any changes. If remote changed then pull changes. If there are any issues/errors -> if errors/issues -> fetch/pull again -> if ok then push

