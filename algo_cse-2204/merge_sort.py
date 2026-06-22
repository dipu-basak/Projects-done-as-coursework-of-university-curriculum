import random

def merge(arr, left, mid, right):
    L = arr[left:mid+1]
    R = arr[mid+1:right+1]

    i = j = 0
    k = left

    
    while i < len(L) and j < len(R):
        if L[i] <= R[j]:
            arr[k] = L[i]
            i += 1
        else:
            arr[k] = R[j]
            j += 1
        k += 1

    
    while i < len(L):
        arr[k] = L[i]
        i += 1
        k += 1

    
    while j < len(R):
        arr[k] = R[j]
        j += 1
        k += 1

def merge_sort(arr, left, right):
    if left >= right:
        return

    mid = (left + right) // 2
    
    merge_sort(arr, left, mid)
    merge_sort(arr, mid + 1, right)
    merge(arr, left, mid, right)


if __name__ == "__main__":
    n = int(input("n= "))
    arr = []
    
    for _ in range(n):
        arr.append(random.randint(10, 100))
    print("Unsorted= ", arr)

    merge_sort(arr, 0, len(arr))
    print(arr)

