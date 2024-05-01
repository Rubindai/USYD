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

n_B, B = int(input()), []  # Every vertex in B has only one edge

for _ in range(n_B):
    B.append(int(input()))

temp1 = 0
while temp1 in B:
    temp1 += 1
mst_weight = 0.
i = 0
pq = []
ls = []
pq.append((0, temp1))
ls.append((temp1, 0))
for i in range(n):

    pq.append((float("inf"), i))
    ls.append((i, float("inf")))
pq.remove((float("inf"), temp1))
ls.remove((temp1, float("inf")))
ls.sort()


temp_index = float("inf")
i = 0
while i < len(B):
    for j in range(n):

        if (adjacency_list[j][B[i]]) < 0:
            w = adjacency_list[j][B[i]]
            mst_weight += abs(w)
            if B[i] == temp1:

                pq.remove((0, B[i]))
                # ls.remove((B[i], 0))
                # ls.sort()
            else:
                pq.remove((float("inf"), B[i]))
                # ls.remove((B[i], float("inf")))
                # ls.sort()
            for k in range(n):
                adjacency_list[B[i]][k] = float("inf")
                adjacency_list[k][B[i]] = float("inf")

            temp_index = i
    if (temp_index != float("inf")):
        B[temp_index] = -1
        temp_index = float("inf")

    i += 1


ls_min = []
for i in B:
    if (i == -1):
        continue
    pq.remove((float("inf"), i))
    # ls.remove((i, float("inf")))
    # ls.sort()
    for j in range(n):
        if j in B:
            continue
        ls_min.append(adjacency_list[i][j])
    ls_min.sort()
    mst_weight += ls_min[0]
    ls_min = []

for i in B:
    if i == -1:
        continue
    for j in range(n):

        adjacency_list[j][i] = float("inf")
        adjacency_list[i][j] = float("inf")


adjacency_list[temp1][temp1] = 0


S = []
# pq.append((0, temp1))
# ls.append((temp1, 0))
heapq.heapify(pq)
ls.sort()
while pq:
    u = heapq.heappop(pq)
    S.append(u[1])
    # if (u[0] == float("inf")):
    #     continue
    mst_weight += abs(u[0])
    # for i in range(len(S)):
    # if S[-1] in B:
    #     for z in range(n):
    #         adjacency_list[S[-1]][z] =
    # float('inf')
    #         adjacency_list[z][S[-1]] = float('inf')

    for v in range(n):
        if (adjacency_list[u[1]][v]) != float("inf"):
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
# print('{:.2f}'.format(mst_weight))
print(mst_weight)
