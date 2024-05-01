import heapq

n = int(input())
m = int(input())

# Initialize adjacency matrix
adjacency_list = [[float("inf")] * n for _ in range(n)]

# Read regular edges
for _ in range(m):
    a = input().split()
    index_i, index_j, weight = int(a[0]), int(a[1]), float(a[2])
    adjacency_list[index_i][index_j] = weight
    adjacency_list[index_j][index_i] = weight

# Read special 'A' edges with weight modification
n_A = int(input())
for _ in range(n_A):
    a = input().split()
    index_i, index_j = int(a[0]), int(a[1])
    # Negating weight (assuming purposeful for your problem)
    adjacency_list[index_i][index_j] *= -1
    adjacency_list[index_j][index_i] *= -1

# Read 'B' vertices
n_B = int(input())
B = set()
for _ in range(n_B):
    B.add(int(input()))

# Start vertex that's not in B
start_vertex = 0
while start_vertex in B and start_vertex < n:
    start_vertex += 1

# Priority queue setup
pq = []
heapq.heappush(pq, (0, start_vertex))
in_mst = [False] * n
mst_weight = 0.0
key = [float('inf')] * n
key[start_vertex] = 0

while pq:
    current_weight, u = heapq.heappop(pq)
    if in_mst[u]:
        continue
    in_mst[u] = True
    mst_weight += abs(current_weight)

    for v in range(n):
        if adjacency_list[u][v] < key[v] and not in_mst[v]:
            key[v] = adjacency_list[u][v]
            heapq.heappush(pq, (key[v], v))

            # If v is in B, block further connections immediately after connecting it
            if v in B:
                for k in range(n):
                    adjacency_list[v][k] = float('inf')
                    adjacency_list[k][v] = float('inf')
                break  # Stop processing other connections from v

print('{:.2f}'.format(mst_weight))
