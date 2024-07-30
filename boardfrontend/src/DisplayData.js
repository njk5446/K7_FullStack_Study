import React, { useState, useEffect } from 'react';
const DataDisplay = () => {
    const [dataBoard, setDataBoard] = useState([]);
    let board = {
        title: '제목',
        writer: '고길동',
        content: '내용'
    }

    let reviseBoard = {
        title: '변경된 제목',
        writer: '둘리',
        content: '변경된 내용'
    }

    let id = 9

    const getBoards = async () => {
        await fetch('http://localhost:8080/board')
            .then(resp => {
                return resp.json();
            }).then(result => {
                setDataBoard(result);
            }).catch(error => {
                console.error('Error fetching Board:', error);
            });
    };

    const getBoard = async () => {
        await fetch('http://localhost:8080/board/' + id)
            .then(resp => {
                return resp.json();
            }).then(result => {
                setDataBoard(result);
            }).catch(error => {
                console.error('Error fetching Board:', error);
            });
    };


    const insertBoard = async () => {
        await fetch('http://localhost:8080/board', {
            method: 'POST'
            ,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'jwt-token'
            },
            body: JSON.stringify(board)
        })
            .then(resp => {
                return resp.json();
            }).then(result => {
                setDataBoard(result);
            }).catch(error => {
                console.error('Error fetching Board:', error);
            });
    };

    const updateBoard = async () => {
        await fetch('http://localhost:8080/board/' + id, {
            method: 'PUT'
            ,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'jwt-token'
            },
            body: JSON.stringify(reviseBoard)
        })
            .then(resp => {
                return resp.json();
            }).then(result => {
                setDataBoard(result);
            }).catch(error => {
                console.error('Error fetching Board:', error);
            });
    };

    const deleteBoard = async () => {
        await fetch('http://localhost:8080/board/' + id, {
            method: 'DELETE'
            ,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'jwt-token'
            },
            body: JSON.stringify(board)
        })
            .then(resp => {
                return resp.json();
            }).then(result => { 
                if(result == null) {
                    console.log(result)
                }
                setDataBoard(result);
            }).catch(error => {
                console.error('Error fetching Board:', error);
            });
    };

    const loadData = () => {
        return (
            <table align="center">
                <thead>
                    <tr>
                        <th>ID</th><th>title</th><th>writer</th>
                        <th>content</th><th>createDate</th>
                    </tr>
                </thead>
                <tbody>
                    {dataBoard.map(board => (
                        <tr key={board.id}>
                            <td>{board.id}</td>
                            <td>{board.title}</td>
                            <td>{board.writer}</td>
                            <td>{board.content}</td>
                            <td>{board.createDate}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        );
    };
    return (
        <div>
            <h2>Data Display</h2>
            <button onClick={() => getBoards()}>getBoards</button>
            <button onClick={() => getBoard()}>getBoard</button>
            <button onClick={() => insertBoard()}>insertBoard</button>
            <button onClick={() => updateBoard()}>updateBoard</button>
            <button onClick={() => deleteBoard()}>deleteBoard</button>
            <div>{loadData()}</div>
        </div>
    );
};

export default DataDisplay