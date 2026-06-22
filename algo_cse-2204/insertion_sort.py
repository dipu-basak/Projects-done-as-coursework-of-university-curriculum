from random import randint

n = int(input("n= "))
arr = []
for _ in range(n):
    arr.append(randint(1, 100))
print("Unsorted list =", arr)

# n = len(arr)

for i in range(1, n): 
    key = arr[i]
    j = i - 1

    while j >= 0 and arr[j] > key:
        arr[j + 1] = arr[j]
        j -= 1

    
    arr[j + 1] = key

print("Sorted list =", arr)
