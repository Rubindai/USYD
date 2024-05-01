# Read in the number of vertices (n) and edges (m)
import heapq


n = int(input())
m = int(input())

# Read the edges from stdin.
edges = []
adjacency_list = []
heap_list = []
for i in range(n):
    adjacency_list.append([float("inf")]*n)


for _ in range(m):
    a = input().split()
    index_i = int(a[0])
    index_j = int(a[1])
    weight = float(a[2])
    adjacency_list[index_i][index_j] = weight
    adjacency_list[index_j][index_i] = weight


# Read the A edges. You may want to use a different data-structure.
n_A, A = int(input()), []

for _ in range(n_A):
    a = input().split()
    index_i = int(a[0])
    index_j = int(a[1])
    adjacency_list[index_i][index_j] = adjacency_list[index_i][index_j]*(-1)
    adjacency_list[index_j][index_i] = adjacency_list[index_j][index_i]*(-1)

n_B, B = int(input()), []

for _ in range(n_B):
    B.append(int(input()))

temp1 = 0
while temp1 in B:
    temp1 += 1


adjacency_list[temp1][temp1] = 0
mst_weight = 0.
pq = []
ls = []
pq.append((0, temp1))
ls.append((temp1, 0))
ls.sort()
for i in range(n):

    pq.append((float("inf"), i))
    ls.append((i, float("inf")))
pq.remove((float("inf"), temp1))
ls.remove((temp1, float("inf")))

S = []
heapq.heapify(pq)
while pq:
    u = heapq.heappop(pq)
    S.append(u[1])
    if (abs(u[0]) == float('inf')):
        print(u)
        break
    else:
        mst_weight += abs(u[0])

    if len(S) > 1 and S[-1] in B:
        for z in range(n):
            adjacency_list[S[-1]][z] = float('inf')
            adjacency_list[z][S[-1]] = float('inf')

    for v in range(n):
        if (adjacency_list[u[1]][v]) != float('inf'):
            if v not in S and adjacency_list[u[1]][v] < ls[v][1]:
                for k in pq:

                    if k[1] == ls[v][0]:
                        pq.remove(k)
                        pq.append((adjacency_list[u[1]][v], v))
                        del ls[v]
                        ls.append((v, adjacency_list[u[1]][v]))
                        ls.sort()
                        heapq.heapify(pq)

                        break

            # Print the weight of the mst to two decimal-places.
print('{:.2f}'.format(mst_weight))
