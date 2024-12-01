#计数排序

def count_sort(nums):
    if not nums:
        return []

        # 找到数组中的最大值和最小值，确定计数范围
    max_value = max(nums)
    min_value = min(nums)
    range_of_values = max_value - min_value + 1

    # 创建计数数组，初始化为0
    count_array = [0] * range_of_values

    # 统计每个元素出现的次数
    for num in nums:
        count_array[num - min_value] += 1

    # 计算计数数组的前缀和，确定每个元素在排序后数组中的位置
    for i in range(1, range_of_values):
        count_array[i] += count_array[i - 1]

    # 创建排序后的数组
    sorted_array = [0] * len(nums)

    # 将元素放入排序后数组的正确位置
    for num in reversed(nums):
        sorted_array[count_array[num - min_value] - 1] = num
        count_array[num - min_value] -= 1

    return sorted_array
if __name__ == '__main__':
    nums = [5,1,1,2,0,0]
    res = count_sort(nums)
    print(res)