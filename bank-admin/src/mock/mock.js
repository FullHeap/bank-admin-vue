import Mock from 'mockjs'

const Random = Mock.Random

// post请求,带参数,参数会在data中返回,会返回url,type,body三个参数,可以把data打印出来看看
Mock.mock('/captchaImage', 'get', (data) => {
  // 请求传过来的参数在body中,传回的是json字符串,需要转义一下
  const info= JSON.parse(data.body)
  return {
    //img: Random.image('200x100', '#4A7BF7', info.name),
    img: Random.image('200x100'),
    uuid: "FXBANK0001"
  }
})

//用户登录
Mock.mock('/login', 'post', (data) => {
  console.log(data)
  let obj = {
    token: 1,
    city: Random.city(),
    color: Random.color()
  }
  return require('./json/userInfo')
})
Mock.mock('/getInfo', 'get', require('./json/userInfo'));
Mock.mock('/logout', 'post', require('./json/userInfo'));
Mock.mock('/getRouters', 'get', require('./json/menu'));
Mock.mock('/system/role/list', 'get', (data)=>{
    console.log(data)
    return require('./json/roleInfo')
});
Mock.mock('/system/role/list', 'get', (data)=>{
  console.log(data)
  return require('./json/roleInfo')
});
Mock.mock('/system/role', 'post', (data)=>{
  console.log('角色新增 data：'+JSON.stringify(data))
  return require('./json/roleInfo')
});
Mock.mock('/system/role/1', 'get', (data)=>{
  console.log('data：'+JSON.stringify(data))
  return require('./json/roleAdmin')
});
Mock.mock(RegExp('/system/role' + '.*'), 'get', require('./json/roleInfo'));

Mock.mock('/system/menu/list', 'get', (data)=>{
  console.log('菜单data：'+JSON.stringify(data))
  return require('./json/menuInfo')
});

Mock.mock('/system/menu', 'post', (data)=>{
  console.log('菜单新增data：'+JSON.stringify(data))
  return require('./json/menuInfo')
});

/* Mock.mock('/system/menu/treeselect', 'get', (data)=>{
  console.log('菜单data：'+JSON.stringify(data))
  return require('./json/menuTree')
}); */

Mock.mock('/system/menu/treeselect', 'get', (data)=>{
  console.log('菜单data：'+JSON.stringify(data))
  return require('./json/menuTransfer')
});


