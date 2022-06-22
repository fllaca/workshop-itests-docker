# Kubernetes basics

We will work with the examples from the [../kubernetes](../kubernetes) folder.

## 4.1 Your first Kubernetes cluster

Check the great tool [Kind](https://kind.sigs.k8s.io/), and create your first Kubernetes cluster with:

```
kind create cluster
```

See the nodes with `kubectl get nodes`

## 4.2 The minimal deployment in Kubernetes: a Pod

```
kubectl apply -f kubernetes/basic-deployment/pod.yaml
```

```
kubectl get pods
```

```
kubectl port-forward web 8080:80
```

## 4.3 Scaling up: Kubernetes "deployments"

```
kubectl apply -f kubernetes/basic-deployment/deployment.yaml
```

```
kubectl get pods
```

## 4.4 Ingress

Let's install the [Nginx Ingress controller](https://kubernetes.github.io/ingress-nginx/deploy/#installation-guide) with:

```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.2.0/deploy/static/provider/cloud/deploy.yaml
```

Then, we will forward all `:8080` traffic to the controller:
```
kubectl port-forward --namespace=ingress-nginx service/ingress-nginx-controller 8080:80
```

Now, we install a service and the ingress resource:
```
kubectl apply -f kubernetes/basic-deployment/service.yaml -f kubernetes/basic-deployment/ingress.yaml
```

We can now sen traffic to our application!:

bash:
```
while true; do curl http://demo.localdev.me:8080/ && sleep 2; done
```

powershell:
```
while ($true) { Invoke-WebRequest http://demo.localdev.me:8080; start-sleep -seconds 2}
```

You can play around with deleting and scaling pods.

## Advance use

### Accessing services running outside Kind

[../kubernetes/docker-host](../kubernetes/docker-host)


### Persistent Volumes

[../kubernetes/volumes](../kubernetes/volumes)

