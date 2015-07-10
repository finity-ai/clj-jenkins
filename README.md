clj-jenkins:
A simple clojure client for jenkins builder.

## Usage

```clj
(with-jenkins "jenkins.smallrivers.com/jenkins"
              {:password "pass", :username "admin"}
              (list-builds "clj-jenkins-master"))
              
(with-jenkins "jenkins.smallrivers.com/jenkins"
              {:password "pass", :username "admin"}
              (list-jobs))
              
(with-jenkins "jenkins.smallrivers.com/jenkins"
              {:password "pass", :username "admin"}
              (list-artifacts "clj-jenkins-master"))
              
(with-jenkins "jenkins.smallrivers.com/jenkins"
              {:password "pass", :username "admin"}
              (submit-job "clj-jenkins-master"))
```

## Redistribution

Copyright (C) 2011 Smallrivers 

Redistribution prohibited