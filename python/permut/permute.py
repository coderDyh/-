#全排列
from typing import List

ans = []
def permute( nums: List[int]) -> List[List[int]]:
    res = []
    back(nums, res)
    print(ans)
    return ans

def back(nums, res:List[int]):
    # print(len(res))
    if len(res) == len(nums):
        # print(res)
        ans.append(res)
        print(ans)
        return

    for i in range(len(nums)):
        if nums[i] in res:
            continue
        # print(nums[i])
        res.append(nums[i])
        back(nums, res)
        # print(res)
        res.pop()
        # res.remove(len(res) - 1)
if __name__ == '__main__':
    nums = [1,2,3]
    res = permute(nums)
    # print(res)
