# managed hosting lab
Test environment for a typical manged hosting environment used for testing. Easy to use with vagrant. Easy to to destroy. Easy to re-deploy with a simple ansible playbook. `192.168.33.10` is the service ip. The loadbalancers speak with the application servers. The application server speak with the database server.



## Setup

| Hostname                         | IP            | Role               | Software                               |
| :------------------------------- | ------------- | ------------------ | -------------------------------------- |
| kmille-solutions-lb01.cloud.dev  | 192.168.33.11 | Loadbalancer       | corosync/pacemaker + haproxy           |
| kmille-solutions-lb02.cloud.dev  | 192.168.33.12 | Loadbalancer       | corosync/pacemaker + haproxy           |
| kmille-solutions-app01.cloud.dev | 192.168.33.21 | Application Server | tomcat8 hosting a simple java  servlet |
| kmille-solutions-app02.cloud.dev | 192.168.33.22 | Application Server | tomcat8 hosting a simple java  servlet |
| kmille-solutions-db01.cloud.dev  | 192.168.33.31 | Database-Server    | MySQL-Server                           |



## How to use it
```bash
kmille@linbox managed-hosting-testing master % vagrant up
kmille@linbox managed-hosting-testing master % cp config_lab ~/.ssh
kmille@linbox managed-hosting-testing master % # Append `Include ~/.ssh/config_lab` to ~/.ssh/config
kmille@linbox managed-hosting-testing master % ansible-playbook site.yaml
kmille@linbox managed-hosting-testing master % curl -v http://192.168.33.10/api/status 
*   Trying 192.168.33.10:80...
* Connected to 192.168.33.10 (192.168.33.10) port 80 (#0)
> GET /api/status HTTP/1.1
> Host: 192.168.33.10
> User-Agent: curl/7.70.0
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 OK
< Server: Apache-Coyote/1.1
< Content-Type: application/json;charset=ISO-8859-1
< Content-Length: 16
< Date: Sun, 24 May 2020 18:26:26 GMT
< X-Loadbalancer: kmille-solutions-lb02
< X-Backend: app01
< 
{"state": "up"}
* Connection #0 to host 192.168.33.10 left intact

```





### things to test (before using it in production)

#### loadbalancer 

- stop/remove pacemaker/corosync without touching the resources it handles

- split-brain between loadbalancer

- upgrading software/the cluster

- lb01 sees lb02 but lb02 does not see lb01

- what happens if haproxy dies and goes down (e.g. reproducible segfault)?

    

 

### TODO

- try out some Monitoring Systems (Zabbix)

    - monitor tomcat
        - how to monitor tomcat application in general?
        - monitor status codes
        - monitor amount of requests
        - monitor response times

- build a three-node-cluster

- move a resource in pacemaker with a time constraint

- take a look at `crm` (seems like a bash script wrapper, use -d )

- make some load tests with `ab`

    
