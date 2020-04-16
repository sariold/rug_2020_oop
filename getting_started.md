# Getting Started
Welcome to the University of Groningen's Object Oriented Programming course! We're glad to have you. Read the instructions provided here carefully so that you can have a smooth start to the course.

## Setting Up
To begin, you'll need to download this repository you're looking at right now. To do that, run the following command (where `<repository_url>` is the URL of this repository):

```
git clone <repository_url>
```

This will download your repository to the current working directory, into a folder with the same name as the repository's name. This is all you need to do. When assignments are made available, you'll see them appear in your repository in a new branch with a name similar to `__assignment_1`, so to avoid issues please do not create a branch that's prefixed with two underscores (it'll be deleted).

## Developing Your Code and Submitting
While previous knowledge of git may be useful, it should not really be needed for you to do well in this course, as long as you know how to submit each assignment.

When you have finished working on your code, you should first add any new files which you have created (`-A` means "all files". Be careful, since you should not upload compiled class files).

```
git add -A
```

Now, commit all changes that have been made (`-a` means "all staged files", and `-m` allows you to supply a commit message. If you do not add `-m`, git will try to open a text editor for you to write a message in).

```
git commit -a -m "This is a short message about what I did."
```

To make these committed changes public, you need to upload them to the remote repository. This is done by 'pushing'.

```
git push
```

If you refresh the page for your repository on github.com, you should now see changes in whatever branch you pushed to (most likely `development`).

### Submission
Once you've committed and push a few times, and you feel that your code is ready to be submitted, create a new pull request from your `development` branch onto the `master` branch. When you do this, a continuous integration service will compile your code and run `mvn integration-test` to see that all tests (if any) pass. If your code does not compile or pass all tests, don't bother making a submission; we won't grade it. Fix it and submit a working version.

If you have made a pull request, but then would like to make some last-minute changes, you can do this by simply adding more commits to the `development` branch. Any pushed commits will simply be added to an existing pull request.

## Questions, Comments, Concerns
Should you have any questions about Github, the above workflow, or other technical questions, please first see if Google can help, and then if you still cannot figure out what to do, make an issue in your repository that adheres to the following guidelines:

* What is the problem?
* What have you already tried to do to fix it?
* How can my teaching assistant replicate the problem?

By giving us this information, you make it much easier for us to give meaningful feedback as quickly as possible.
