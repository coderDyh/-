
def insert_sort(arr):
    n = len(arr)
    for i in range(1, n):
        key = arr[i]
        j = i - 1
        while j >= 0 and arr[j] > key:
            arr[j + 1] = arr[j]
            j -= 1
        arr[j + 1] = key
    return arr

if __name__ == '__main__':
    arr = [3,2,1,7,0,4,5]
    res = insert_sort(arr)

    print(res)