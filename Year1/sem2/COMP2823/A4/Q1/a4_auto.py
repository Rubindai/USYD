import heapq

# Number of vertices and edges
n = int(input())
m = int(input())
adjacency_matrix = []
for i in range(n):

    adjacency_matrix.append([float('inf')]*n)


for i in range(n):
    adjacency_matrix[0][0] = 0

# Read in all the edges
for _ in range(m):
    a = input().split()
    index_i = int(a[0])
    index_j = int(a[1])
    weight = float(a[2])

    adjacency_matrix[index_i][index_j] = weight
    adjacency_matrix[index_j][index_i] = weight


n_A = int(input())
for _ in range(n_A):
    a = input().split()
    index_i = int(a[0])
    index_j = int(a[1])

    adjacency_matrix[index_i][index_j] *= -1
    adjacency_matrix[index_j][index_i] *= -1

# Prim's algorithm
in_mst = [False] * n
key_values = [float('inf')] * n  # min value for two connected vertex
min_heap = []

# Start with the first vertex
key_values[0] = 0
heapq.heappush(min_heap, (0, 0))

mst_weight = 0.0

while min_heap:
    key, u = heapq.heappop(min_heap)
    if in_mst[u]:
        continue

    # Mark this node as included in the MST
    in_mst[u] = True
    mst_weight += abs(key)  # Add the edge weight

    # Update keys of adjacent vertices
    for v in range(n):
        weight_uv = adjacency_matrix[u][v]
        if weight_uv != float('inf') and not in_mst[v] and weight_uv < key_values[v]:
            key_values[v] = weight_uv
            heapq.heappush(min_heap, (weight_uv, v))

# Print the total weight of the MST
print(f"{mst_weight:.2f}")
