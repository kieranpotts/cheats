# Git cheats

#### Contents

- [Staging](#staging)
- [Committing](#committing)
- [Branching](#branching)
- [Remotes](#remotes)
  - [Fetch and Pull](#fetch-and-pull)
  - [Push](#push)
- [Merging](#merging)
  - [Merge Options](#merge-options)
- [Undoing](#undoing)
  - [Checkout](#checkout)
  - [Resetting](#resetting)
  - [Reverting](#reverting)
- [Deleting](#deleting)
- [Submodules](#submodules)

## Staging

Stage specific files:

```sh
$ git add [file1] [file2] [file3]
```

Stage all changes:

```sh
$ git add (--all|-A)
```

Stage "updated" files (modifications and deletions) but not new files:

```sh
$ git add (--update|-u)
```

The behaviour of `git add .` changed between Git v1.x and Git v2.x. It Git v1, `git add .` staged new and modified files, but did not handle deleted files. In Git v2, `git add .` became an alias for `git add --all`, staging all files – new, modified and deleted.

Git v2 also introduced `git add --ignore-removal .`, which provided the behaviour of `git add .` from v1, staging new and modified files but ignored deleted ones.

## Committing

```sh
$ git commit -m "Comment"
```

## Branching

To list all local branches:

```sh
$ git branch
```

To list all branches, local branches and mapped remote ones:

```sh
$ git branch (--all|-a)
```

Create a new branch from the current checked-out code:

```sh
$ git branch <branch_name>
```

Create a new branch from another existing branch:

```sh
$ git branch <new_branch_name> <existing_branch_name>
```

Switch to a different branch:

```sh
$ git checkout <local_branch>
```

Or, from Git v2.23, you can use the `git switch` command instead:

```sh
$ git switch <local_branch>
```

To create _and_ switch to a new branch in a single command, use either of the following:

```sh
$ git checkout --branch <branch_name>
$ git checkout -b <branch_name>
```

From Git v2.23 you can also use:

```sh
$ git switch --create|-c <branch_name>
```

To create a new branch with no parents, and to clear the directory contents so you are starting a new revision history with no contents:

```sh
$ git checkout --orphan <branch_name>
$ git rm --cached -r .
```

## Remotes

The `git fetch`, `git pull` and `git push` commands are used to sync locally-cloned repositories with the original remotely-hosted ("upstream") repositories.

The `git remote` command specifies what endpoints the sync commands will operate on.

#### Fetch and Pull

The `git fetch` and `git pull` commands are used to download remote content.

Of the two commands, `git fetch` is considered to be the "safe" option. The `fetch` operation will download remote content but not alter the state of the local repository. The `pull` operation will download remote content _and_ immediately attempt to change the local state to match. This may cause the local repository to enter into a conflicted state.

`git pull` is actually an alias for two separate commands: `git fetch & git merge`. All changes from the point where the local and upstream branches diverged are downloaded, then a merge workflow is executed, producing a new local merge commit that contains all of the new content from the remote. Diverged changes on the remote may have been committed over multiple commits; all of those diverged changes will be merged locally in a single commit with a combined log message.

Rebasing is an alternative strategy to resolve divergence between tracked branches. Passing the `--rebase` option to the `git pull` command triggers the rebase strategy. It is the equivalent of `git fetch & git rebase`. Instead of the diverged changes in the remote being combined in a single merge commit in the local, rebasing takes all of the new commits on the remote and replays them verbatim on the local. Thus, multiple commits may be appended to the local history.

`git pull --rebase` ensures a linear history that is consistent between local and remote repositories. And it prevent unnecessary merge commits. It also moves locally diverged changes so they stay on top of what everyone else has contributed to the remote. For these reasons, the rebasing strategy is widely preferred in source control workflows. It can be made the default strategy for the `git pull` command by a global configuration option:

```sh
$ git config --global branch.autosetuprebase always
```

#### Push

The `git push` command is used to upload changes to a remote repository.

By default, `git push` pushes local changes in the current checked-out branch. To push all branches:

```
git push --all
```

The first time you `git push` a new branch, you should use the `--set-upstream` flag (or its alias `-u`), to create a relationship between the local branch and the equivalent one on the remote. It is not necessary that branches be mapped to other branches of the same name, although it is conventional to do so.

```
git push dev -u <remote_name> <remote_branch_name>
```

Example:

```
git push dev -u origin dev
```

You can use `git push --all -u` to push all local branches to the default remote, and to setup tracking for all of the new branches automatically.

## Merging

The `git merge` command joins two or more development histories together.

#### Merge Options

- `--ff`: Default behaviour. When the merge resolves as a fast-forward, the branch pointer is updates and no merge commit is created.
- `--no-ff`: Create an explicit merge commit even when the merge resolves as a fast-forward.
- `--ff-only`: All merges must be fast-forwardable or a `MergeException` will be thrown. This option never creates an explicit merge commit.
- `--squash`: Collapses all the incoming commits into a single new commit directly to the target branch. Does not record an explicit merge.
- `--squash --ff-only`: Collapses all the incoming commits into a single commit directly to the target branch, never creating a merge, but does so only if the source branch can be fast-forwarded. If not, a `MergeException` is thrown.

## Undoing

#### Checkout

Use `git checkout <commit>` to return to a previous commit. This will detach your HEAD, that is to say leave you with no branch checked out:

```sh
$ git checkout 0d1d7fc32
```

If you want to introduce new commits from this position in the history, you will need to do so on a new branch:

```sh
$ git checkout -b old-state 0d1d7fc32
```

#### Resetting

The `reset` command properly deletes recent commits. There are three main options:

- `--soft` resets changes but keeps them staged in your working tree.
- `--hard` will lose the changes altogether.
- The default mode will keep the changes in the working tree but not on the index, so changes will need to be `git add`ed before being re-committed.

The following command will destroy any local modifications that are not yet staged and committed, ie resetting your local directory to the state of the last valid commit in your local repository:

```sh
$ git reset --hard HEAD
```

(`HEAD` is the last commit in your current branch.)

Alternatively, if there's current work that you want to keep, stash it first...

```sh
$ git stash
$ git reset --hard HEAD
$ git stash pop
```

... or you can do a default or `--soft` reset:

```sh
$ git reset (--soft) HEAD
```

If `--soft` does not work, try `--mixed` or `--keep`.

To reset the last commit, plus recent changes since that commit:

```sh
$ git reset (--soft|--hard) HEAD~1
```

Or, to reset to an even earlier specific commit in your local branch:

```sh
$ git reset (--soft|--hard) 0d1d7fc32
```

You may need to resolve conflicts if you've modified things that were changed since the commit you reset to.

Resetting changes history. To push the new history to a remote repository, use the `--force` flag. (It still may not be possible to push the changes into the upstream repository if your Git hosting services adds an authentication layer to protect certain branches from mutations.)

You can also reset your local branch to match what's on a remote server, in this case the "origin" server:

```sh
$ git fetch --all
$ git reset --hard origin/<local_branch>
```

#### Reverting

The `revert` command is a softer approach to undoing changes. It creates a new commit with the reverse patch, thus history is not changed.

This will create three separate revert commits:

```sh
$ git revert a867b4af 25eee4ca 0766c053
```

`git revert` also takes ranges. This will revert the last two commits:

```sh
$ git revert HEAD~2..HEAD
```

Similarly, you can revert a range of commits using commit hashes:

```sh
$ git revert a867b4af..0766c053 
```

To revert a merge commit:

```sh
$ git revert -m 1 <merge_commit_sha>
```

A slightly harder revertion strategy is to use the `--no-commit` flag. The behaviour is more like a hard `reset`.

```sh
$ git revert --no-commit <commit>
```

Use the `--strategy` option to use a certain [merge strategy](https://git-scm.com/docs/git-merge#_merge_strategies). The **resolve** strategy is generally considered safe and fast.

```sh
$ git revert --strategy resolve <commit>
```

#### Rebasing

Alternatively, you can use the `rebase` command to move the `HEAD` of the current branch back to an earlier commit.

```sh
$ git rebase -i HEAD~x
```

(Note: `x` is the number of commits to revert.)

This will open a test file. Type `d` or `drop` next to the commits that you want to remove.

If the commits you dropped were already pushed to the remote repository, you will need to use `git push --force` or `git push --force-with-lease`.

#### Restoring

Git v2.23 introduces `git restore` as an easier way of specifying which files to change. See the [official documentation for examples](https://git-scm.com/docs/git-restore/2.23.0). This is an experimental feature and may change or be dropped entirely in future versions of Git.

## Deleting

To delete a local Git branch, use either of the following commands:

```sh
$ git branch --delete <branch_name>
$ git branch -d <branch_name>
```

To force delete the branch, regardless of its current push or merge status, use either of these two commands:

```sh
$ git branch --delete --force <branch_name>
$ git branch -D <branch_name>
```

To delete a remote branch, use either of these commands:

```sh
$ git push <remote_name> --delete <branch_name>
$ git push <remote_name> :<branch_name>
```

Your local repository will still have tracking references to the remote branches, even if they no longer exist (see `git branch -a`). To clean up old tracking references, use the `git prune` command:

```sh
$ git prune remote <remote_name>
```

## Stashing

Create a new stash:

```sh
$ git stash save "<stash_name>"
```

List all the stashes stored on the stack:

```sh
$ git stash list
```

To apply a stash and remove it from the stack:

```sh
$ git stash pop stash@{n}
```

To apply a stash but keep in on the stack, too:

```sh
$ git stash apply stash@{n}
```

## Tags

To list all tags:

```sh
$ git tag -l
```

To delete a local tag:

```sh
$ git tag -d <tag_name>
```

To delete a remote tag:

```sh
$ git push --delete origin <tag_name>
```

## Submodules

Removing a submodule takes some effort. Follow these steps:

1. Delete the relevant section from the `.gitmodules` file
2. Stage the `.gitmodules` changes: `git add .gitmodules`
3. Delete the relevant section from `.git/config`
4. Run `git rm --cached path_to_submodule` (no trailing slash)
5. Run `rm -rf .git/modules/path_to_submodule` (no trailing slash)
6. Commit: `git commit -m "Removed submodule"`
7. Delete the now untracked submodule files: `rm -rf path_to_submodule`

## Fixing a corrupted Git repository

If only your local repo is corrupted, and you know the URL to the remote, you can fully reset your local `.git` directory, and still keep any recent work you have not yet committed, with the following sequence of commands:

```
mv -v .git .git_old               # Move old .git directory
git init                          # Initialise a new Git repo
git remote add origin [url]       # Create a new reference to the upstream repository
git fetch                         # Get the history from the upstream repository
git reset origin/main --mixed     # Update the local history to the upstrea's history
```

This leaves your working tree intact, only affecting Git's internal bookkeeping. Thus, any changes you started making to files before the `.git` directory was corrupted should not be revealed as un-staged changes.

However, any submodules will probably need to be deleted and then re-initialised: `git submodule update --init`.
