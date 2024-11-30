from typing import List


def sortArray(nums: List[int]) -> List[int]:
    n = len(nums)
    gap = n//2
    while gap > 0:
        for i in range(gap, n):
            curr = nums[i]
            preIdx = i - gap
            while preIdx >= 0 and curr < nums[preIdx]:
                nums[preIdx + gap] = nums[preIdx]
                preIdx -= gap
            nums[preIdx + gap] = curr
        gap = gap//2
    return nums

if __name__ == '__main__':
    arr = [4, 8, 9, 6, 7, 2]
    res = sortArray(arr)
    print(arr)