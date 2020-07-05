# Locker new

## 业务规则

- locker 可以存包取包
- PrimaryLockerRobot按照Locker顺序存，它只管理M号Locker
- SuperLockerRobot将包存入空置率最大的Locker，它制管理L号Locker
- LockerRobotManager只管理一个Locker（S号）、一个PrimaryLockerRobot（管理一个Locker）和 SuperLockerRobot（管理一个Locker）。考虑后续会管理更多
- LockerRobotManager可以委派Robot存包取包，也可以自己存包取包，委派没有顺序
- LockerRobotManager管理的Locker和Robot 不会直接对外提供服务
- 不同型号Locker产生的票据不通用，当用不同的型号取包时，系统要提示票的型号不对
- 超市管理员在配置Robot和Manager的时候，只要Locker的型号选择不对，Robot和Manager将无法正常使用

## Tasking

| Given                                                        | When      | Then                                                  |
| ------------------------------------------------------------ | --------- | ----------------------------------------------------- |
| S型号的Locker，初始容量为1，可用容量为1                      | 存包      | 获得票                                                |
| M型号的Locker，初始容量为1，可用容量为0                      | 存包      | 提示Locker已满                                        |
| S型号的Locker，初始容量为1，可用容量为1, M 型包              | 存包      | 提示大小不匹配                                        |
| S型号的Locker，有效的S票                                     | 取包      | 取包成功                                              |
| S型号的Locker，有效的M票                                     | 取包      | 提示票类型不匹配                                      |
| S型号的Locker，无效票                                        | 取包      | 提示票无效                                            |
| PrimaryLockerRobot 管理M Locker1 和Locker2，都有可用容量     | 存包      | 包存入Locker1，获得票                                 |
| PrimaryLockerRobot 管理M Locker1 和Locker2，Locker1无可用容量 | 存包      | 包存入Locker2，获得票                                 |
| PrimaryLockerRobot 管理M Locker1 和Locker2，Locker1都无可用容量 | 存包      | 提示Locker已满                                        |
| PrimaryLockerRobot 管理M Locker1 和Locker2，有效的M票        | 取包      | 取包成功                                              |
| PrimaryLockerRobot 管理M Locker1 和Locker2，无效票           | 取包      | 提示票无效                                            |
| PrimaryLockerRobot和S型Locker                                | 配置Robot | Locker类型不匹配，PrimaryLockerRobot只能配置M型Locker |
| SuperLockerRobot 管理L Locker1 和Locker2，可用容量和容量分别为 2,2; 3,3 | 存包      | 包存入Locker2，获得票                                 |
| SuperLockerRobot 管理L Locker1 和Locker2，，可用容量和容量分别为 3,3; 2,2 | 存包      | 包存入Locker1，获得票                                 |
| SuperLockerRobot 管理L Locker1 和Locker2，Locker1都无可用容量 | 存包      | 提示Locker已满                                        |
| SuperLockerRobot 管理L Locker1 和Locker2，有效的L票          | 取包      | 取包成功                                              |
| SuperLockerRobot 管理L Locker1 和Locker2，无效票             | 取包      | 提示票无效                                            |
| SuperLockerRobot和S型Locker                                  | 配置Robot | Locker类型不匹配，SuperLockerRobot只能配置L型Locker   |
| LockerRobotManager管理S型Locker、PrimaryLockerRobot、SuperRobotLocker，都有可用容量 | 存S型包   | 存包成功，包存入Locker                                |
| LockerRobotManager管理S型Locker、PrimaryLockerRobot、SuperRobotLocker，都有可用容量 | 存M型包   | 存包成功，包存入PrimaryLockerRobot                    |
| ockerRobotManager管理S型Locker、PrimaryLockerRobot、SuperRobotLocker，都有可用容量 | 存L型包   | 存包成功，包存入SuperLockerRobot                      |
| LockerRobotManager管理S型Locker、PrimaryLockerRobot、SuperRobotLocker, 无可用容量是 | 存包      | 存包失败，提示Locker已满                              |
| LockerRobotManager管理S型Locker、PrimaryLockerRobot、SuperRobotLocker，有效的S型票 | 取包      | 取包成功                                              |
| LockerRobotManager管理S型Locker、PrimaryLockerRobot、SuperRobotLocker，有效的M型票 | 取包      | 取包成功                                              |
| LockerRobotManager管理S型Locker、PrimaryLockerRobot、SuperRobotLocker，有效的L型票 | 取包      | 取包成功                                              |
| LockerRobotManager管理S型Locker、PrimaryLockerRobot、SuperRobotLocker，无效票 | 取包      | 取包失败，提示票无效                                  |

