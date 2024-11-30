#冒泡排序
def bubble_sort(arr):
    n = len(arr)
    for i in range(n):
        for j in range(0, n-i-1):
            if arr[j] > arr[j+1]:
                temp = arr[j]
                arr[j] = arr[j+1]
                arr[j+1] = temp
    return arr



if __name__ == '__main__':
    arr = [4,8,9,6,7,2]
    res = bubble_sort(arr)
    print(res)