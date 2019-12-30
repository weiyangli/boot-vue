function computedCount(success, fail) {
    let num = Math.random() * 2;
    console.log("num ====>" + num);
    // 执行事件 1 秒后返回结果
    setTimeout(() => {
        if (num > 1) {
            console.log('call resolve()...');
            success('200 OK');
        } else {
            console.log('call reject()...');
            fail('timeout in ' + num + ' seconds.');
        }
    }, 2000);
    console.log("end...");
}

test();
// var p1 = new Promise(computedCount);
// var p2 = p1.then(function (result) {
//     console.log('成功：' + result);
// });
// var p3 = p2.catch(function (reason) {
//     console.log('失败：' + reason);
// });

async function test () {
    let result =  await testPromise();
}

function testPromise() {
    console.log("进入方法");
    new Promise((success, fail) => {
        let num = Math.random() * 2;
        console.log("num ====>" + num);
        setTimeout(() => {
            if (num > 1) {
                success("ok")
            } else {
                fail('no');
            }
        }, 2000);
    }).then((data) => {
        console.log(data);
    }).catch((data) => {
        console.log(data);
    })
    console.log("结束方法");
}
