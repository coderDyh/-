#选择排序
#双重循环遍历数组，每经过一轮比较，找到最小元素的下标，将其交换至首位

def select_port(arr):
    n = len(arr)
    for i in range(n):
        minInx = i
        for j in range(i+1, n):
            if arr[j] < arr[minInx]:
                minInx = j
        temp = arr[minInx]
        arr[minInx] = arr[i]
        arr[i] = temp
    return arr

if __name__ == '__main__':
    arr = [4,8,9,6,7,2]
    res = select_port(arr)
    print(res)