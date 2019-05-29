using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DDRGenerator : MonoBehaviour
{
	public DDRConfiguration[] configs;
	public GameObject left, down, up, right;
	public GameObject perfObj, goodObj, badObj, missObj;

	[HideInInspector]
	public float speed;
	public Animator muAnim;

	private float badThreshold = 389, goodThreshold = 407, PerfectThreshold = 419, goodThreshold2 = 432, badThreshold2 = 437, missThreshold = 452;
	private int myCfg;

	public bool bad;
	public bool good;
	public bool perfect;
	public float yPos;
    // Start is called before the first frame update

    void Start()
    {
		myCfg = Random.Range(0, configs.Length -1);
		if (configs[myCfg].left)
			left.SetActive(true);
		if (configs[myCfg].right)
			right.SetActive(true);
		if (configs[myCfg].up)
			up.SetActive(true);
		if (configs[myCfg].down)
			down.SetActive(true);
	}
	void Hit(string which)
	{
		if(which == "miss")
		{
			Instantiate(missObj, transform.position,transform.rotation,transform.parent.parent.parent);
			Destroy(gameObject);
		}
		else if (which == "bad")
		{
			Instantiate(badObj, transform.position, transform.rotation, transform.parent.parent.parent);
			MCP.DDRC.score += 1;
			Destroy(gameObject);
		}
		else if (which == "good")
		{
			Instantiate(goodObj, transform.position, transform.rotation, transform.parent.parent.parent);
			MCP.DDRC.score += 1.5f;
			Destroy(gameObject);
		}
		else if (which == "perfect")
		{
			Instantiate(perfObj, transform.position, transform.rotation, transform.parent.parent.parent);
			MCP.DDRC.score += 2;
			Destroy(gameObject);
		}
		if(which != "miss")
		{
			if (myCfg == 0)
				muAnim.SetTrigger("Up");
			if (myCfg == 1)
				muAnim.SetTrigger("Down");
			if (myCfg == 2)
				muAnim.SetTrigger("Left");
			if (myCfg == 3)
				muAnim.SetTrigger("Right");
			if (myCfg == 4)
				muAnim.SetTrigger("UpDown");
			if (myCfg == 5)
				muAnim.SetTrigger("LeftRight");
			if (myCfg == 6)
				muAnim.SetTrigger("UpLeft");
			if (myCfg == 7)
				muAnim.SetTrigger("UpRight");
			if (myCfg == 8)
				muAnim.SetTrigger("DownLeft");
			if (myCfg == 9)
				muAnim.SetTrigger("DownRight");
		}
	}
    // Update is called once per frame
    void Update()
	{
		yPos = transform.localPosition.y;
		if (Input.anyKeyDown && ((configs[myCfg].down && Input.GetButton("DDRdown") || (!configs[myCfg].down && !Input.GetButton("DDRdown"))) && (configs[myCfg].up && Input.GetButton("DDRup") || (!configs[myCfg].up && !Input.GetButton("DDRup"))) && (configs[myCfg].left && Input.GetButton("DDRleft") || (!configs[myCfg].left && !Input.GetButton("DDRleft"))) && (configs[myCfg].right && Input.GetButton("DDRright") || (!configs[myCfg].right && !Input.GetButton("DDRright")))))
		{
			if (yPos > badThreshold && yPos < goodThreshold)
				Hit("bad");
			if (yPos > goodThreshold && yPos < PerfectThreshold)
				Hit("good");
			if (yPos > PerfectThreshold && yPos < goodThreshold2)
				Hit("perfect");
			if (yPos > goodThreshold2 && yPos < badThreshold2)
				Hit("good");
			if (yPos > badThreshold2)
				Hit("bad");
		}
		if (yPos > missThreshold)
			Hit("miss");
		transform.position += new Vector3(0, speed * 20 * Time.deltaTime,0);
    }
}
[System.Serializable]
public class DDRConfiguration
{
	public string name;
	public bool left, down, up, right;
}
