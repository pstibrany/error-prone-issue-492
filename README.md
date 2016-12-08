# Test case for [error-prone, issue 492](https://github.com/google/error-prone/issues/492)

Available ant targets:

* `ep14` – compiles Main.java using error-prone 2.0.14 (`build-ep14`), and then runs it (`run`)
* `ep15` – compiles Main.java using error-prone 2.0.15 (`build-ep15`), and then runs it (`run`)

When compiled using 2.0.15, I see this error when running the compiled class.

    [java] java.lang.NoSuchMethodError: com.google.common.collect.Sets$SetView.iterator()Lcom/google/common/collect/UnmodifiableIterator;
    [java] 	at org.apache.tools.ant.taskdefs.ExecuteJava.execute(ExecuteJava.java:195)
    [java] 	at org.apache.tools.ant.taskdefs.Java.run(Java.java:833)
    [java] 	at org.apache.tools.ant.taskdefs.Java.executeJava(Java.java:227)
    [java] 	at org.apache.tools.ant.taskdefs.Java.executeJava(Java.java:136)
    [java] 	at org.apache.tools.ant.taskdefs.Java.execute(Java.java:109)
    [java] 	at org.apache.tools.ant.UnknownElement.execute(UnknownElement.java:293)
    [java] 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    [java] 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    [java] 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    [java] 	at java.lang.reflect.Method.invoke(Method.java:497)
    [java] 	at org.apache.tools.ant.dispatch.DispatchUtils.execute(DispatchUtils.java:106)
    [java] 	at org.apache.tools.ant.Task.perform(Task.java:348)
    [java] 	at org.apache.tools.ant.Target.execute(Target.java:435)
    [java] 	at org.apache.tools.ant.Target.performTasks(Target.java:456)
    [java] 	at org.apache.tools.ant.Project.executeSortedTargets(Project.java:1405)
    [java] 	at org.apache.tools.ant.Project.executeTarget(Project.java:1376)
    [java] 	at org.apache.tools.ant.helper.DefaultExecutor.executeTargets(DefaultExecutor.java:41)
    [java] 	at org.apache.tools.ant.Project.executeTargets(Project.java:1260)
    [java] 	at org.apache.tools.ant.Main.runBuild(Main.java:854)
    [java] 	at org.apache.tools.ant.Main.startAnt(Main.java:236)
    [java] 	at org.apache.tools.ant.launch.Launcher.run(Launcher.java:285)
    [java] 	at org.apache.tools.ant.launch.Launcher.main(Launcher.java:112)
    [java] Caused by: java.lang.NoSuchMethodError: com.google.common.collect.Sets$SetView.iterator()Lcom/google/common/collect/UnmodifiableIterator;
    [java] 	at Main.main(Unknown Source)
    [java] 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    [java] 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    [java] 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    [java] 	at java.lang.reflect.Method.invoke(Method.java:497)
    [java] 	at org.apache.tools.ant.taskdefs.ExecuteJava.run(ExecuteJava.java:218)
    [java] 	at org.apache.tools.ant.taskdefs.ExecuteJava.execute(ExecuteJava.java:153)
    [java] 	... 21 more
    [java] Java Result: -1

Notice that bytecode of target class is different when using two different error-prone versions.

    $ diff javap-ep14.txt javap-ep15.txt 
    2,3c2,3
    <   Last modified Dec 8, 2016; size 1022 bytes
    <   MD5 checksum 182a451e7eaa537523fb03ff4c2f60aa
    ---
    >   Last modified Dec 8, 2016; size 1050 bytes
    >   MD5 checksum d7df4c02b0ad98c07d131f88054170ae
    15c15
    <    #7 = Methodref          #31.#32        // com/google/common/collect/Sets$SetView.iterator:()Ljava/util/Iterator;
    ---
    >    #7 = Methodref          #31.#32        // com/google/common/collect/Sets$SetView.iterator:()Lcom/google/common/collect/UnmodifiableIterator;
    40c40
    <   #32 = NameAndType        #53:#54        // iterator:()Ljava/util/Iterator;
    ---
    >   #32 = NameAndType        #53:#54        // iterator:()Lcom/google/common/collect/UnmodifiableIterator;
    62c62
    <   #54 = Utf8               ()Ljava/util/Iterator;
    ---
    >   #54 = Utf8               ()Lcom/google/common/collect/UnmodifiableIterator;
    99c99
    <         21: invokevirtual #7                  // Method com/google/common/collect/Sets$SetView.iterator:()Ljava/util/Iterator;
    ---
    >         21: invokevirtual #7                  // Method com/google/common/collect/Sets$SetView.iterator:()Lcom/google/common/collect/UnmodifiableIterator;
    