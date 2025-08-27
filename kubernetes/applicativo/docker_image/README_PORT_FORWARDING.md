Usare Port Forwarding
Puoi utilizzare il comando kubectl port-forward per reindirizzare una porta locale a un pod o a un servizio. Ad esempio, per reindirizzare la porta 8090 del tuo computer alla porta 8089 del servizio ping-service-nodeport nel namespace test, esegui il seguente comando:

kubectl port-forward svc/ping-service-nodeport 8090:8089 -n test


#ip del nodo
kubectl get nodes -o wide
ip_del_nodo:ports.NodePort per accedere al pod dall'interno del cluster


#esempi di chiamate
curl http://ping-service-nodeport:8089/api/ping
curl http://ping-service-nodeport.test.svc.cluster.local:8089/api/ping

# Service con Load Balancing
# I Service in Kubernetes utilizzano il bilanciamento del carico (load balancing) automaticamente. Se hai più istanze di un Pod dietro lo stesso Service, le chiamate # REST verranno distribuite tra i vari Pod. Questo garantisce una distribuzione equa del carico.



Namespace Differenti
Se i tuoi Pod si trovano in namespace differenti, dovrai specificare il namespace nella chiamata REST. Per esempio, se my-rest-service si trova nel namespace production, la chiamata sarà:

curl http://my-rest-service.production.svc.cluster.local/endpoint